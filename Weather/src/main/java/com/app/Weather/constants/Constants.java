package com.app.Weather.constants;

public class Constants {

	
	
	public class csv{
	public static final String CSV_PATH = "/csv";
	public static final String DIGEST = "/digest";
	
	public static final String DIGEST_SUCCESS = "CSV data loaded successfully.";
	public static final String DIGEST_FAILED = "Failed to load CSV data";	
	
	public static final String ERROR_LOADING_CSV_FILE = "Error loading CSV file: ";
	
	
	public static final String CSV_BATCH_SIZE = "${csv.batch.size}";
	public static final String CSV_THREAD_NUM = "${csv.thread.num}";
	
	public static final String CSV_FILE_PATH_ONE = "${csv.file.path1}";
	public static final String CSV_FILE_PATH_TWO = "${csv.file.path2}";
	public static final String CSV_FILE_PATH_THREE = "${csv.file.path3}";

	
	}
	
	public class weather{
		
		public static final String WEATHER_PATH = "/weather";
		public static final String INSIGHT = "/insight";
		
		public static final String VERY_HOT = "veryHot";
		public static final String COLD_AND_RAINY = "rainyAndCold";

		public static final String MIN_LONGITUDE = "-180";
		public static final String MAX_LONGITUDE = "180";
		
		public static final String MIN_LATITUDE = "-90";
		public static final String MAX_LATITUDE = "90";
		
		public static final String INVALID_LONGITUDE = "Invalid longitude. Valid range is -";
		public static final String INVALID_LATITUDE = "Invalid latitude. Valid range is -";	
		public static final String INVALID_CONDITION= "Invalid condition. Valid options are 'veryHot' or 'coldAndRainy'";	



		
		}
	
}
