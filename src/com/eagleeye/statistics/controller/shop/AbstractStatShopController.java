package com.eagleeye.statistics.controller.shop;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.controller.AbstractStatController;

public abstract class AbstractStatShopController extends AbstractStatController {

	protected IStatAchievementBsh statAchievementBsh;

	public AbstractStatShopController() {
		super();
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
	}
}
