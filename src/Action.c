Action()
{

	web_url("FibnacciService", 
		"URL=http://54.183.236.44:8080/FibnacciService/", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=", 
		"Snapshot=t1.inf", 
		"Mode=HTML", 
		LAST);

	lr_think_time(38);
	
	
	lr_start_transaction("AskFib");


	web_submit_form("fibonacci.do", 
		"Snapshot=t2.inf", 
		ITEMDATA, 
		"Name=number", "Value={NewParam_1}", ENDITEM, 
		"Name=submit", "Value=submit", ENDITEM, 
		LAST);

	
	lr_end_transaction("AskFib", LR_AUTO);


	return 0;
}