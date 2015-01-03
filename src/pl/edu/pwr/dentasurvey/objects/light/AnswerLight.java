package pl.edu.pwr.dentasurvey.objects.light;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class AnswerLight implements Serializable{
	private static final long serialVersionUID = -7887861321267745750L;
	
	private String[] answer;
	private Long questionId;	
	private Long answeredSurveyId;
	
	public String answersToString() {
		String res = "";
		if(answer != null) {
			for(String ans : answer) {
				res += ans + "; ";
			}
		}
		
		return res;
	}
}
