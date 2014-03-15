package com.zitec.workshopz.workshopz.storage.mappers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.storage.mappers.UserMapper;
import com.zitec.workshopz.workshopz.entities.Workshop;

public class WorkshopzMapper extends BaseStorageMapper {

	@Override
	public BaseEntity hydrate(HashMap<String, String> data) {
		Workshop item = new Workshop();
		
		item.setRemoteId(data.get("remote_id"));
		item.setName(data.get("name"));
		item.setStartDate(data.get("start_date"));
		item.setEndDate(data.get("end_date"));
		item.setTheme(data.get("theme"));
		item.setRating(data.get("rating"));
		item.setCurrency(data.get("currency"));
		item.setCost(data.get("cost"));
		
		UserMapper uMapper = new UserMapper();
		item.setSpeakers(uMapper.hydrate(this.parseSpeakers(data.get("speakers"))));
		return item;
	}
	
	public HashMap<String, Object> dehydrate(Workshop data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("remote_id", data.getRemoteId());
		response.put("name", data.getName());
		response.put("start_date", data.getStartDate());
		response.put("end_date", data.getEndDate());
		response.put("theme", data.getTheme());
		response.put("rating", data.getRating());
		response.put("currency", data.getCurrency());
		response.put("cost", data.getCost());
		
		UserMapper uMapper = new UserMapper();
		ArrayList<HashMap<String, String>> obj = new ArrayList<HashMap<String, String>>();
		for(User user : data.getSpeakers()){
			obj.add(uMapper.dehydrate(user));
		}
		response.put("speakers", obj);
		
		return response;
	}

	public ArrayList<HashMap<String, String>> parseSpeakers(String speakers)
	{
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		try {
			JSONArray spks = new JSONArray(speakers);
			int spksNr = spks.length();
			for(int i = 0; i < spksNr; i++){
				JSONObject jsonUsr = spks.getJSONObject(i);
				HashMap<String, String> usr = new HashMap<String, String>();
				usr.put("remote_id", jsonUsr.getString("id"));
				usr.put("name", jsonUsr.getString("name"));
				usr.put("position", jsonUsr.getString("position"));
				usr.put("phone_number", jsonUsr.getString("phone_number"));
				usr.put("email", jsonUsr.getString("email"));
				results.add(usr);
			}
			return results;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(BaseEntity item){
		System.out.println(this.dehydrate((Workshop)item));
	}

	@Override
	public HashMap<String, String> dehydrate(BaseEntity data) {
		return null;
	}
}
