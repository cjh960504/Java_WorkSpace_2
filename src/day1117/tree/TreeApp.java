/*
	데이터간 구조화된 포함관계를 포함할 때 흔히 사용되는 Tree를 배워보자!!
*/
package day1117.tree;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeApp extends JFrame{
	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {
		//트리에 보여질 노드를 구성해보자
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("카테고리");
		createNode(top); // 반지, 귀걸이, 팔찌를 부착할 예정 , 따라서 최상위 노드를 넘겨주겠음
		tree = new JTree(top); //여기에 노드를 생성자 인수로 넣어줄 예정
		scroll = new JScrollPane(tree);
		
		add(scroll);
		
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void createNode(DefaultMutableTreeNode top) {
		//넘겨받은 탑 노드에 원하는 하위 노드를 생성하여 자식 노드로 붙이자!!
		DefaultMutableTreeNode act= new DefaultMutableTreeNode("액세사리");
		DefaultMutableTreeNode[] node = new DefaultMutableTreeNode[4];
		node[0] = new DefaultMutableTreeNode("반지");
		node[1] = new DefaultMutableTreeNode("목걸이");
		node[2] = new DefaultMutableTreeNode("귀걸이");
		node[3] = new DefaultMutableTreeNode("팔찌");
		
		DefaultMutableTreeNode shoes= new DefaultMutableTreeNode("신발");
		DefaultMutableTreeNode[] node2 = new DefaultMutableTreeNode[4];
		node2[0] = new DefaultMutableTreeNode("슬리퍼");
		node2[1] = new DefaultMutableTreeNode("운동화");
		node2[2] = new DefaultMutableTreeNode("고무신");
		node2[3] = new DefaultMutableTreeNode("구두");
		
		for(int i=0;i<node.length;i++) {
			act.add(node[i]);
			shoes.add(node2[i]);
		}
		top.add(act);
		top.add(shoes);
	}
	public static void main(String[] args) {
		new TreeApp();
	}
}
