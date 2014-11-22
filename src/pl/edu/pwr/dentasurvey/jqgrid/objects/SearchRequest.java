package pl.edu.pwr.dentasurvey.jqgrid.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SearchRequest {
	private int rows;
	private int page;
	private String sidx;
	private String sord;
}
