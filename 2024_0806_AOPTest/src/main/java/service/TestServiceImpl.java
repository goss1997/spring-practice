package service;

import java.util.List;

import dao.TestDao;

public class TestServiceImpl implements TestService {

	//Setter Injection
	TestDao test_dao;
	
	public void setTest_dao(TestDao test_dao) {
		this.test_dao = test_dao;
	}


	@Override
	public List<String> sido_list() {
		// TODO Auto-generated method stub

        try {

            Thread.sleep(1234);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return test_dao.sido_list();
	}

}
