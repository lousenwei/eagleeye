package com.eagleeye.user.blh.impl;

import java.util.List;

import com.eagleeye.user.blh.IGroupMemberBlh;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;

public class GroupMemberBlhImpl implements IGroupMemberBlh {

	GroupMemberDAO groupMemberDao;

	@Override
	public GroupMemberEO getFirstGroupMemberBlh(String managerId) {
		// TODO Auto-generated method stub
		return groupMemberDao.getFirstGroupMember(managerId);
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	@Override
	public void saveGroupMember(GroupMemberEO member) {
		// TODO Auto-generated method stub
		groupMemberDao.saveOrUpdate(member);
	}

	public void saveGroupMembers(List<GroupMemberEO> members) {
		groupMemberDao.saveOrUpdateBatch(members);
	}

	@Override
	public List<GroupMemberEO> getGroupMemebersByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return groupMemberDao.findByManagerId(managerId);
	}

	public void deleteGroupMember(GroupMemberEO member) {
		groupMemberDao.delete(member);
	}

	public void deleteGroupMemberByStaffId(String staffId) {
		groupMemberDao.deleteGroupMemberByStaffId(staffId);
	}
}
