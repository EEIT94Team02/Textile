package tw.com.eeit94.textile.model.member;

import java.util.List;

import tw.com.eeit94.textile.model.member.util.MemberKeyWordsBean;

/**
 * 控制會員基本資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface MemberDAO {

	public List<MemberBean> selectAll();

	public List<MemberBean> select(MemberBean mbean);

	public List<MemberBean> insert(MemberBean mbean);

	public List<MemberBean> update(MemberBean mbean);

	public List<MemberBean> delete(MemberBean mbean);

	public List<MemberBean> selectByKeyWords(MemberKeyWordsBean mbean);

	public List<MemberBean> selectByName(MemberKeyWordsBean mkwbean);

	public List<MemberBean> selectBySimilarName(MemberKeyWordsBean mkwbean);
	
	public List<MemberBean> selectByEmail(MemberKeyWordsBean mkwbean);
}