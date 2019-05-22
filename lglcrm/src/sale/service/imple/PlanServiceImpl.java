package sale.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sale.dao.PlanDao;
import sale.pojo.Plan;
import sale.pojo.Sale;
import sale.service.PlanService;

@Service(value="planService")
public class PlanServiceImpl implements PlanService {
	@Resource(name="planDao")
	private PlanDao planDao;
	
	public PlanDao getPlanDao() {
		return planDao;
	}

	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}

	
	@Override
	public List<Plan> find() {
		return planDao.find();
	}
	
	@Override
	public List<Plan> find(Sale s) {
		return planDao.find(s);
	}
	
	@Override
	public boolean add(Plan plan) {
		return planDao.add(plan);
	}

	@Override
	public boolean del(Plan plan) {
		return planDao.del(plan);
	}

	@Override
	public boolean update(Plan plan) {
		return planDao.update(plan);
	}

}
