package com.eagleeye.user.bsh.impl;

import java.util.List;

import com.eagleeye.user.blh.IGroupMemberBlh;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

public class GroupMemberBshImpl implements IGroupMemberBsh {
	private IGroupMemberBlh groupMemberBlh;

	@Override
	public GroupMemberEO getFirstGroupMemberBlh(String managerId) {
		// TODO Auto-generated method stub
		return groupMemberBlh.getFirstGroupMemberBlh(managerId);
	}

	public IGroupMemberBlh getGroupMemberBlh() {
		return groupMemberBlh;
	}

	public void setGroupMemberBlh(IGroupMemberBlh groupMemberBlh) {
		this.groupMemberBlh = groupMemberBlh;
	}

	@Override
	public void saveGroupMember(GroupMemberEO member) {
		// TODO Auto-generated method stub
		groupMemberBlh.saveGroupMember(member);
	}

	public void saveGroupMembers(List<GroupMemberEO> members) {
		groupMemberBlh.saveGroupMembers(members);
	}

	@Override
	public List<GroupMemberEO> getGroupMemebersByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return groupMemberBlh.getGroupMemebersByManagerId(managerId);
	}

	public void deleteGroupMember(GroupMemberEO member) {
		groupMemberBlh.deleteGroupMember(member);
	}

	public void deleteGroupMemberByStaffId(String staffId) {
		groupMemberBlh.deleteGroupMemberByStaffId(staffId);
	}
}
