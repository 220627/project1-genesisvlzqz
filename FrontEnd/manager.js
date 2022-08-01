const url = "http://localhost:3000";
document.getElementById("getReimbButton").onclick = getUserReimbursements
//document.getElementById("createReimbButton").onclick = createReimbursement
//document.getElementById("submitReimbButton").onclick = submitReimbursement
document.getElementById("processReimbButton").onclick = processReimbursements
document.getElementById("logOutButton").onclick = logOut
checkLogin()

async function getUserReimbursements(){

    checkLogin();

    let response = await fetch(url + "/reimbursements")
    document.getElementById("table-row").style.visibility = "visible" 
    document.getElementById("dataContainer").innerHTML = ""
    //success
    
    if(response.status===200){
        
        let data = await response.json();
        console.log(data) 
        var oTblReport = $("#reimbTable")
        oTblReport.DataTable ({
            "data" : data,
            "columns" : [
                { "data" : "reimb_id" },
                { "data" : "reimb_amount" },
                { "data" : "dateSubmitted" },
                { "data" : "reimb_description" },
                { "data" : "author.ers_username"},
                { "data" : "resolver.ers_username",
                  "defaultContent": "N/A"},
                { "data" : "type.reimb_type" },
                { "data" : "status.reimb_status"},
                { "data":   null,
                    "defaultContent": '<select id="statSel" class="editor-active"><option value=1>Pending</option><option value=2>Approved</option><option value=3>Denied</option></select>'
                },
                {"data":   null,
                 "defaultContent": '<input type="checkbox" value="data.reimb_id" class="editor-active">'    
                }
            ],
  
        });

        $( "#processReimbButton" ).click(function() {
                alert(oTblReport.columns().checkboxes.selected().length);
          });
    }

}
async function processReimbursements(){


}
async function logOut(){
    window.sessionStorage.clear()
    window.sessionStorage.removeItem('user')
    window.sessionStorage.removeItem('id')
    window.sessionStorage.removeItem('perm')
    window.location.href = "home.html";
}
function checkLogin(){
    
    if(window.sessionStorage.getItem('id')>0){
        if(window.sessionStorage.getItem('perm')==1){
               
        document.getElementById("loginRow").innerHTML = ""
        document.getElementById("welcomeHead").innerText = "Welcome, " + window.sessionStorage.getItem('user')
        document.getElementById("welcomeHead").style.color = "black"
        document.getElementById("getReimbButton").style.visibility="visible"
        document.getElementById("logOutButton").style.visibility = "visible"
        document.getElementById("footer").style.visibility = "visible"
        document.getElementById("managerMenu").style.visibility = "visible"
    }   else {
        window.location.href = "home.html";
        window.sessionStorage.clear();
        window.alert("Insufficient Rights");
    }
    }    
}
function refresh(){
    window.location.reload("Refresh")
  }
  