package com.eagleeye.statistics.controller.staffDtl;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.controller.AbstractStatController;

public abstract class AbstractStatStaffDtlController extends AbstractStatController {

	protected IStatAchievementBsh statAchievementBsh;

	public AbstractStatStaffDtlController() {
		super();
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
	}
}
