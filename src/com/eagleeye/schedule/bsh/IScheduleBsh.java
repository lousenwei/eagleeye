package com.eagleeye.schedule.bsh;

import java.util.List;

public interface IScheduleBsh {

	public List<?> selectedTasks() throws Exception;

	public void modifyTaskStatus(List<?> updates, String status);
}
