package lt2;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeTapping {

	public static void main(String[] args) {
		new TreeTapping().tap();

	}

	private void tap() {
		DefaultMutableTreeNode person = new DefaultMutableTreeNode(new String("person"));
		
		DefaultMutableTreeNode employee = new DefaultMutableTreeNode(new String("employee"));
		DefaultMutableTreeNode sales_rep = new DefaultMutableTreeNode(new String("sales_rep"));
		DefaultMutableTreeNode engineer = new DefaultMutableTreeNode(new String("engineer"));
		
		
		DefaultMutableTreeNode customer = new DefaultMutableTreeNode(new String("customer"));
		DefaultMutableTreeNode us_customer = new DefaultMutableTreeNode(new String("us_customer"));
		DefaultMutableTreeNode local_customer = new DefaultMutableTreeNode(new String("local_customer"));
		DefaultMutableTreeNode regional_customer = new DefaultMutableTreeNode(new String("regional_customer"));
		DefaultMutableTreeNode non_us_customer = new DefaultMutableTreeNode(new String("non_us_customer"));
		
		
		person.add(employee);
		employee.add(sales_rep);
		employee.add(engineer);
		
		person.add(customer);
		customer.add(us_customer);
		customer.add(non_us_customer);
		
		us_customer.add(local_customer);
		us_customer.add(regional_customer);
		
		DefaultMutableTreeNode root = person;

		System.out.println("BreadthFirst:");
		treeNummerate(root.breadthFirstEnumeration());
		System.out.println("");
		System.out.println("Preorder:");
		treeNummerate(root.preorderEnumeration());
		System.out.println("");
		System.out.println("Postorder:");
		treeNummerate(root.postorderEnumeration());
		
		
		
	}

	
	private void treeNummerate(Enumeration<DefaultMutableTreeNode> e){
		while (e.hasMoreElements()) {
			Object object = (Object) e.nextElement();
			System.out.println("\t"+object.toString());
		}
	}

}
