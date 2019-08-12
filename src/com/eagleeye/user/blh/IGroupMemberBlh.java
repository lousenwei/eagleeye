package com.eagleeye.user.blh;

import java.util.List;

import com.eagleeye.user.eo.GroupMemberEO;

public interface IGroupMemberBlh {

	public GroupMemberEO getFirstGroupMemberBlh(String managerId);

	public void saveGroupMember(GroupMemberEO member);

	public void saveGroupMembers(List<GroupMemberEO> members);

	public List<GroupMemberEO> getGroupMemebersByManagerId(String managerId);

	public void deleteGroupMember(GroupMemberEO member);

	public void deleteGroupMemberByStaffId(String staffId);
}
