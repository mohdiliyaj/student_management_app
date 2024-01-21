package in.ashokit.service;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.entity.CounsellorEntity;

public interface ICounsellorService {
	public boolean saveCounsellor(CounsellorEntity c);
	public CounsellorEntity loginCheck(String email,String password);
	public boolean recoverPassword(String email);
	public DashboardResponse getDashboardInfo(Integer counsellorId);
}
