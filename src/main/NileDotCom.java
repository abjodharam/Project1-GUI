package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;


public class NileDotCom extends JFrame {

	private JPanel contentPane;
	
	private JTextField tfID;
	private JTextField tfQuantity;
	
	private JLabel lblDetails;
	private JLabel lblID;
	private JLabel lblDets;
	private JLabel lblSub;
	private JLabel lblQuantity;
	private JLabel lblSubtotal;
	
	private JButton btnConfirmItem;
	private JButton btnView;
	private JButton btnFinish;
	private JButton btnProcessItem;
	
	
	private String quantity;
	private String sub;
	
	private int discount;	
	private int clicks = 1;
	private double subtotal = 0;
	private double subDouble = 0;
	private double taxRate = 6;
	
	private String[] cart = new String[100];
	private String[] cartList = new String[100];
	private String[] itemTemp = new String[4];
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();
	
	private DateFormat transID = new SimpleDateFormat("ddMMyyyyHHmm");
	private DateFormat invoiceDate = new SimpleDateFormat("dd/MM/yy, hh:mm:ss a z");
	private Date date = new Date();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NileDotCom frame = new NileDotCom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NileDotCom() {
		setTitle("Nile Dot Com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblID = new JLabel("Enter Item ID for Item #1:");
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID.setBounds(39, 35, 150, 20);
		contentPane.add(lblID);
		
		lblQuantity = new JLabel("Enter Quantity for Item #1:");
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setBounds(27, 66, 162, 14);
		contentPane.add(lblQuantity);
		
		lblDetails = new JLabel("Details for Item #1:");
		lblDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDetails.setBounds(58, 92, 131, 14);
		contentPane.add(lblDetails);
		
		lblSubtotal = new JLabel("Order Subtotal for item(s):");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setBounds(10, 117, 179, 14);
		contentPane.add(lblSubtotal);
		
		tfID = new JTextField();
		tfID.setBounds(192, 35, 400, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(192, 63, 400, 20);
		contentPane.add(tfQuantity);
		
		btnProcessItem = new JButton("Process Item #1");
		btnProcessItem.setAction(action);
		
		btnProcessItem.setBounds(27, 178, 222, 23);
		contentPane.add(btnProcessItem);
		
		btnView = new JButton("View Order");
		btnView.setAction(action_2);
		btnView.setEnabled(false);
		btnView.setBounds(27, 212, 222, 23);
		contentPane.add(btnView);
		
		JButton btnNewOrder = new JButton("New Order");
		btnNewOrder.setAction(action_5);
		btnNewOrder.setBounds(27, 246, 222, 23);
		contentPane.add(btnNewOrder);
		
		btnConfirmItem = new JButton("Confirm Item #1");
		btnConfirmItem.setAction(action_1);
		btnConfirmItem.setEnabled(false);
		btnConfirmItem.setBounds(261, 178, 222, 23);
		contentPane.add(btnConfirmItem);
		
		btnFinish = new JButton("Finish Order");
		btnFinish.setAction(action_3);
		btnFinish.setEnabled(false);
		btnFinish.setBounds(259, 212, 222, 23);
		contentPane.add(btnFinish);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_4);
		btnExit.setBounds(259, 246, 222, 23);
		contentPane.add(btnExit);
		
		lblDets = new JLabel("");
		lblDets.setBounds(192, 92, 400, 14);
		contentPane.add(lblDets);
		
		lblSub = new JLabel("");
		lblSub.setForeground(Color.BLACK);
		lblSub.setBounds(192, 117, 400, 14);
		contentPane.add(lblSub);
	}
		
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Process Item #1");
		}
		public void actionPerformed(ActionEvent e) {
			ProcessItem();
		}
	}
	
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Confirm Item #1");
		}
		public void actionPerformed(ActionEvent e) {
			ConfirmItem();
		}
	}
	
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "View Order");
		}
		public void actionPerformed(ActionEvent e) {
			ViewOrder();
		}
	}
	
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Finish Order");
		}
		public void actionPerformed(ActionEvent e) {
				FinishOrder();
		}
	}
	
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Exit");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "New Order");
		}
		public void actionPerformed(ActionEvent e) {
			NewOrder();
		}
	}
	
	public String[] itemSearchAndReturn (String File,  String itemNum) {
		try {
			Scanner scan = new Scanner(new File(File));
			String[] currentItem = new String[4];
			while (scan.hasNextLine()) {
				currentItem = scan.nextLine().toString().split(", ");
				if(currentItem[0].equals(itemNum)) {
					return currentItem;
				}
			}
		} catch (FileNotFoundException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void ProcessItem() {
		String itemNum = tfID.getText();
		quantity = tfQuantity.getText();
		
		NileDotCom project = new NileDotCom();
		itemTemp = project.itemSearchAndReturn("inventory.txt", itemNum); 
		
		if(itemNum.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Item ID.", "Nile Dot Com - ERROR", JOptionPane.INFORMATION_MESSAGE);
			tfID.setText("");
		}
		
		else if(itemTemp == null) {
			JOptionPane.showMessageDialog(null, "Item ID " + itemNum + " not in file.", "Nile Dot Com - ERROR", JOptionPane.INFORMATION_MESSAGE);
			tfID.setText("");
			tfQuantity.setText("");
		}
		
		if(quantity.trim().isEmpty() || quantity.equals("0")) {
			JOptionPane.showMessageDialog(null, "Please Enter Quantity.", "Nile Dot Com - ERROR", JOptionPane.INFORMATION_MESSAGE);
			tfQuantity.setText("");
		}
		if(itemTemp[2].equals("true")) {
			sub = String.format("%.2f", itemSubtotal(itemTemp, quantity));
			lblDets.setText(itemTemp[0] + " " + itemTemp[1] + " $" + itemTemp[3] + " " + quantity + " " + discount(quantity) + "%" + " $" + sub);
			lblDetails.setText("Details for Item #" + clicks + ":");
			btnConfirmItem.setEnabled(true);
			btnProcessItem.setEnabled(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "Item ID " + itemNum + " not in stock.", "Nile Dot Com - ERROR", JOptionPane.INFORMATION_MESSAGE);
			tfID.setText("");
			tfQuantity.setText("");
		}
	}
	
	public void ConfirmItem() {
		cart[clicks] = (clicks + ". " + lblDets.getText());
		cartList[clicks] = (lblDets.getText());
		lblSubtotal.setText("OrderSubtotal for " + clicks + " item(s):");			
		clicks++;
		btnProcessItem.setText("Process Item #" + clicks);
		lblID.setText("Enter Item ID for Item #" + clicks + ":");
		lblQuantity.setText("Enter Quantity for Item #" + clicks + ":");
		
		btnProcessItem.setEnabled(true);
		btnConfirmItem.setEnabled(false);
		btnView.setEnabled(true);
		btnFinish.setEnabled(true);
		
		subDouble = Double.parseDouble(sub); 
		subtotal += subDouble;
		lblSub.setText("$" + String.format("%.2f", subtotal));
		btnConfirmItem.setText("Confirm Item #" + clicks);
		tfID.setText("");
		tfQuantity.setText("");
	}
	
	public void ViewOrder() {
		JOptionPane.showMessageDialog(null, itemized(), "Nile Dot Com - Current Shopping Cart Status", JOptionPane.INFORMATION_MESSAGE);			
	}
	
	public void FinishOrder() {
		JOptionPane.showMessageDialog(null, invoice() , "Nile Dot Com - FINAL INVOICE", JOptionPane.INFORMATION_MESSAGE);
		tfID.setEditable(false);
		tfQuantity.setEditable(false);
		btnProcessItem.setEnabled(false);
		btnFinish.setEnabled(false);
		
		try {
			File newFile = new File("transactions.txt");
			
			if(!newFile.isFile()) {
					newFile.createNewFile();
			}
			FileWriter fr = new FileWriter(newFile, true);
			BufferedWriter br = new BufferedWriter(fr);
			//br.write(transID.format(date));
			
			for(int i = 1; i < clicks; i++) {
				br.write(transID.format(date) + " " + cartList[i] + invoiceDate.format(date) + "\n");
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void NewOrder() { 
		date = new Date();
		clicks = 1;
		lblID.setText("Enter Item ID for Item #1:");
		lblQuantity.setText("Enter Quantity for Item #1:");
		lblDetails.setText("Details for Item #1:");
		lblDets.setText("");		
		lblSubtotal.setText("Order Subtotal for item(s):");
		lblSub.setText("");
		btnProcessItem.setText("Process Item #1");
		btnConfirmItem.setText("Confirm Item #1");
		subtotal = 0;
		
		tfID.setEditable(true);
		tfQuantity.setEditable(true);
		btnProcessItem.setEnabled(true);
		btnView.setEnabled(false);
		
		
	}
	
	public double itemSubtotal(String[] items, String quantity) {
		discount = discount(quantity);
		double itemSubtotal = Double.parseDouble(items[3]) * Double.parseDouble(quantity); 
		if(discount == 10) {
			itemSubtotal *= .9 ;
		}
		if(discount == 15) {
			itemSubtotal *= .85;
			itemSubtotal = Math.round(itemSubtotal*100.0)/100.0;
		}
		if(discount == 20) {
			itemSubtotal *= .80;			
		}
		return itemSubtotal;
	}
	
	public int discount(String quantity) {
		int amount = Integer.parseInt(quantity);
		int discountPercent = 0;
		if(amount <= 4) {
			discountPercent = 0;
		}
		if(amount >= 5 && amount <=9) {
			discountPercent = 10;
		}
		if(amount >= 10 && amount <= 14) {
			discountPercent = 15;
		}
		else if(amount >= 15) {
			discountPercent = 20;
		}	
		return discountPercent;
	}
	
	public String itemized() {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < clicks; i++) {
			sb.append(cart[i] + "\n");
		}
		return sb.toString();
	}
	
	public String invoice() {
		StringBuilder invoice = new StringBuilder();
		invoice.append("Date: " + invoiceDate.format(date) + "\n\n");
		invoice.append("Number of line items: " + (clicks - 1) + "\n\n");
		invoice.append("Item# /ID / Title / Price / Qty / Disc % / Subtotal:" + "\n\n");
		invoice.append(itemized() + "\n");
		invoice.append("Order Subtotal: $" + (String.format("%.2f", subtotal)) + "\n\n");
		invoice.append("Tax rate: " + taxRate + "%\n\n");
		invoice.append("Tax amount: $" + String.format("%.2f", (subtotal * (taxRate * 0.01))) + "\n\n");
		invoice.append("ORDER TOTAL: $" + String.format("%.2f", (subtotal * (taxRate * 0.01)) + subtotal) + "\n\n");
		invoice.append("Thanks for shopping at Nile Dot Com!");
		return invoice.toString();
	}
}