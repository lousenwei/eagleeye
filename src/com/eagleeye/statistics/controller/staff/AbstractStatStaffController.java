package com.eagleeye.statistics.controller.staff;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.controller.AbstractStatController;

public abstract class AbstractStatStaffController extends AbstractStatController {

	protected IStatAchievementBsh statAchievementBsh;

	public AbstractStatStaffController() {
		super();
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
	}
}
