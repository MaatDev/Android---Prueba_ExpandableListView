package pe.edu.ulima.ceids.prueba_exandable.listview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

//http://es.wikicode.org/index.php/ExpandableListView_personalizado

public class Prueba_ExpandableListViewActivity extends Activity {
    
	private ArrayList<String> groups;
	private ArrayList<ArrayList<ArrayList<ImgTextHolder>>> children;
	
	private ExpandableListView elv_test;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.elv_test = (ExpandableListView) findViewById(R.id.elv_test);
        
        this.cargarDatos();
        
        CustomExpandableAdapter adapter = new CustomExpandableAdapter(this, groups, children);
        
        this.elv_test.setAdapter(adapter);
        
    }
    
    
    class ImgTextHolder{
    	
    	public ImgTextHolder(){
    		
    	}
    	
    	public ImgTextHolder(String text, int image){
    		this.text = text;
    		this.image = image;
    	}
    	
    	public String text;
    	public int image;
    	
    }
    
    class CustomExpandableAdapter extends BaseExpandableListAdapter{

		private ArrayList<String> groups;
		
		private ArrayList<ArrayList<ArrayList<ImgTextHolder>>> children;
		
		private Context context;
		
		public CustomExpandableAdapter(Context context, 
				ArrayList<String> groups, 
				ArrayList<ArrayList<ArrayList<ImgTextHolder>>> children){
			
			this.context = context;
			this.groups = groups;
			this.children = children;

		}
		
		@Override
		public boolean areAllItemsEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Override
		public ArrayList<ImgTextHolder> getChild(int groupPosition, int chieldPosition) {
			
			return this.children.get(groupPosition).get(chieldPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChield, View convertView,
				ViewGroup parent) {
			
			String child_text = ( (ArrayList<ImgTextHolder>) getChild(groupPosition, childPosition) ).get(0).text;
			int child_img = ( (ArrayList<ImgTextHolder>) getChild(groupPosition, childPosition) ).get(0).image;
			
			if(convertView == null){
				LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflator.inflate(R.layout.elv_item_child, null);
			}
			
			TextView tv_child = (TextView) convertView.findViewById(R.id.tv_child_text);
			ImageView img_child = (ImageView) convertView.findViewById(R.id.iv_img);
			
			tv_child.setText(child_text);
			img_child.setImageResource(child_img);
			
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return children.get(groupPosition).size();
		}

		@Override
		public String getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return groups.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
				ViewGroup parent) {
			
			String group = (String) getGroup(groupPosition);
			
			if(convertView == null){
				LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflator.inflate(R.layout.elv_item_group, null);
			}
			
			TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group_text);
			
			tv_group.setText(group);			
			
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
    
    private void cargarDatos(){

    	this.groups = new ArrayList<String>();
    	this.children = new ArrayList<ArrayList<ArrayList<ImgTextHolder>>>();
    	
    	this.groups.add("Juego 1");
    	this.groups.add("Juego 2");
    	
    	this.children.add(new ArrayList<ArrayList<ImgTextHolder>>());
    	
    	this.children.get(0).add(new ArrayList<ImgTextHolder>());
    	this.children.get(0).get(0).add(new ImgTextHolder("Counter Strike 1.6", R.drawable.img1));
    	this.children.get(0).add(new ArrayList<ImgTextHolder>());
    	this.children.get(0).get(1).add(new ImgTextHolder("Counter Strike Source", R.drawable.img2));
    	
    	this.children.add(new ArrayList<ArrayList<ImgTextHolder>>());
    	
    	this.children.get(1).add(new ArrayList<ImgTextHolder>());
    	this.children.get(1).get(0).add(new ImgTextHolder("Half Life", R.drawable.img3));
    	this.children.get(1).add(new ArrayList<ImgTextHolder>());
    	this.children.get(1).get(1).add(new ImgTextHolder("Ricochet", R.drawable.img4));
    	
    	
    }

    
}