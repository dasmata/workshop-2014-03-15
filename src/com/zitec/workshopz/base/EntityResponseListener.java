package com.zitec.workshopz.base;
import java.util.ArrayList;

import com.zitec.workshopz.base.storage.Error;

public interface EntityResponseListener {

	public void onSuccess(ArrayList<BaseEntity> obj);
	public void onError(Error err);
}
