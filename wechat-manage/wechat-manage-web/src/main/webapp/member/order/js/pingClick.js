function pingClick(b,g,i,d,h,f){
	if(b){
		try{
			var j=new MPing.inputs.Click(g);j.event_param=i;
			if(f){
				j.page_param=f
			}
			var a=new MPing();
			a.send(j)
		}catch(c){
			
		}
	}
};