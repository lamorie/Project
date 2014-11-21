package ca.ualberta.cs.cmput301f14t14.questionapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import ca.ualberta.cs.cmput301f14t14.questionapp.data.DataManager;
import ca.ualberta.cs.cmput301f14t14.questionapp.model.Question;
import ca.ualberta.cs.cmput301f14t14.questionapp.view.QuestionListAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchActivity extends Activity {

	private DataManager dataManager;
	private QuestionListAdapter qla = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       	setContentView(R.layout.activity_search);             
        dataManager = DataManager.getInstance(this);
        
        Intent intent = getIntent();
		DataManager dataManager = DataManager.getInstance(getApplicationContext());
		String query = intent.getStringExtra("QUERY_STRING");
        String[] words = query.split(" ");
		//build a question list from this query
        
        List<Question> qList = dataManager.getQuestionList(null);
        List<Question> qSearchList = new ArrayList<Question>();
        Iterator<Question> list = qList.iterator();
		while(list.hasNext()){
			Question question = list.next();
			// if the body or title contains any search word, add question (if not already added)
			for(String word : words) {
				if((question.getBody().contains(word) || question.getTitle().contains(word)) && !qSearchList.contains(question)){
					qSearchList.add(question);
				}
			}
		}
        qla = new QuestionListAdapter(this, R.layout.list_question, qSearchList);  
        ListView questionView = (ListView) findViewById(R.id.question_list);
        questionView.setAdapter(qla);
        questionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// run off to the question view if you tap an item
				final Question question = qla.getItem(position);
				UUID qId = question.getId();
				Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
				intent.putExtra("QUESTION_UUID", qId.toString());
				startActivity(intent);
			}
		});
    }

}