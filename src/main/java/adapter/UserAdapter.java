package adapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.User;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Registered;

public class UserAdapter extends AbstractTableModel{
	private  List<Apustua> apustuak=new ArrayList();
	private User user;
	private String[] colNames = {"Event", "Question", "EventDate", "Bet(€)"}; 

	public UserAdapter(User u) {
		Registered r = (Registered) u;
		this.user = u;
		for(ApustuAnitza aa: r.getApustuAnitzak()) {
			for(Apustua a: aa.getApustuak()) {
				apustuak.add(a);
			}
		}
	}
	@Override
	public String getColumnName(int col) {
		System.out.println(colNames[col]);
		return colNames[col];
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return apustuak.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Apustua a = apustuak.get(rowIndex);
		if(columnIndex==0) {
			return a.getKuota().getQuestion().getEvent();
		}
		else if(columnIndex==1) {
			return a.getKuota().getQuestion();
		}
		else if(columnIndex==2) {
			return a.getApustuAnitza().getData();
		}
		else {
			return a.getApustuAnitza().getBalioa();
		}
		
	}

}
