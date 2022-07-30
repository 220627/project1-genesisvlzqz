package com.revature.daos;

import com.revature.models.ReimbStatus;

public interface ReimbStatusDAOInterface {
	ReimbStatus getStatusById(int status_id);
	ReimbStatus getStatusByName(String status);
}
