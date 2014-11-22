package pl.edu.pwr.dentasurvey.jqgrid.objects;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SearchResponse {
	private int records;
	private int page;
	private String sidx;
	private String sord;
	private int total;
	private List<?> rows; 
}
