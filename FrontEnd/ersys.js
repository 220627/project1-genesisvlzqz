const url = "http://localhost:3000";
document.getElementById("loginButton").onclick = loginFunction
document.getElementById("getReimbButton").onclick = getUserReimbursements
document.getElementById("createReimbButton").onclick = createReimbursement
document.getElementById("submitReimbButton").onclick = submitReimbursement
document.getElementById("logOutButton").onclick = logOut


async function logOut(){
    window.sessionStorage.clear()
    window.sessionStorage.removeItem('user')
    window.sessionStorage.removeItem('id')
    window.sessionStorage.removeItem('perm')
    location.reload()
}
async function loginFunction(){

    username = document.getElementById("username").value 
    let pass = document.getElementById("password").value 

    let userCreds = {
        username: username,
        password: pass
    }

    console.log(userCreds)
    //post request, credentials creates cookie to store session
    let response = await fetch(url + "/login", {
        method: "POST",
        body: JSON.stringify(userCreds),
        credentials: "include"
    })

    console.log(response.status)

    if(response.status===202){

        let data = await response.text();
        response = await fetch(url + "/user/" + username)
        data = await response.json();
        sessionStorage.setItem('user',username);
        sessionStorage.setItem('id',Number(data.ers_users_id));
        sessionStorage.setItem('perm',Number(data.user_role_id));
        if(sessionStorage.getItem('perm')==2){
            document.getElementById("loginRow").innerHTML = ""
            document.getElementById("welcomeHead").innerText = "Welcome, " + sessionStorage.getItem('user')
            document.getElementById("welcomeHead").style.color = "black"
            document.getElementById("getReimbButton").style.visibility="visible"
            document.getElementById("createReimbButton").style.visibility="visible"
            document.getElementById("createForm").style.visibility="hidden"
            document.getElementById("logOutButton").style.visibility="visible"
        } else if (sessionStorage.getItem('perm')==1){
            window.location.href = "manager.html";
        }

    } else {
        let result = await response.text();
        document.getElementById("welcomeHead").innerText = result
        document.getElementById("welcomeHead").style.color = "red"
    }

}

async function getUserReimbursements(){

    checkLogin();
    //get objects in rows
    let response = await fetch(url + "/reimbursements/" + window.sessionStorage.getItem('user'))
    document.getElementById("table-row").style.visibility = "visible" 
    document.getElementById("dataContainer").innerHTML = ""
    //success
    if(response.status===200){
        
        let data = await response.json();
        //iterate through rows
        for (let element of data){

            let row = document.createElement("tr")

            let cell = document.createElement("td");
            cell.innerHTML = element.reimb_id
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.reimb_amount
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.dateSubmitted
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.reimb_description
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.author.ers_username
            row.appendChild(cell)

            cell = document.createElement("td");
            if(element.status.reimb_status!="PENDING"){
                cell.innerHTML = element.resolver.ers_username
            } else {
                cell.innerHTML = "N/A"
            }
            
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.status.reimb_status
            row.appendChild(cell)

            cell = document.createElement("td");
            cell.innerHTML = element.type.reimb_type
            row.appendChild(cell)

            document.getElementById("dataContainer").appendChild(row)
        }
    }

}
async function createReimbursement(){
    var form = document.getElementById('createForm');
    var table = document.getElementById('table-row');

    if (form.style.visibility === 'hidden') {
        form.style.visibility = 'visible';
        table.style.position = 'absolute';
        table.style.bottom = '0px';
        table.style.visibility = 'hidden';
    } else {
        table.style.position = 'static';
      form.style.visibility = 'hidden';
      table.style.visibility = 'visible';
    }

}
async function submitReimbursement(){
    let amount = document.getElementById("amount").value
    let descp = document.getElementById("desc").value
    let status = 1
    let type = document.getElementById("selectType").value
    let data = JSON.stringify({
        "reimb_amount":Number(amount),
        "reimb_author":sessionStorage.getItem('id'),
        "reimb_description":descp,
        "reimb_status_id":Number(status),
        "reimb_type_id":Number(type)
    });

    let response = await fetch(url + "/reimbursement",{
        method:"POST",
        body: data,
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    if(response.status===202){
        document.getElementById("createForm").innerHTML = "<h3>Reimbursement requested. Please allow 5-7 bussiness day for a decision.</h3>"
        + "<p><input type='button' class='btn btn-block btn-success' value='Refresh' onClick='refresh(this)'></p>";
        document.getElementById("createReimbButton").style.visibility = "hidden"
    } else {
        document.getElementById("createForm").innerHTML = "Request FAILED. Please refresh and verify the fields."
    }
}
function checkLogin(){
    if(window.sessionStorage.getItem('user')){
        document.getElementById("loginRow").innerHTML = ""
        document.getElementById("logOutButton").style.visibility = "visible"
        document.getElementById("footer").style.visibility = "visible"
    }    
}
function refresh(){
    window.location.reload("Refresh")
  }