function proveraPraznihPoljaRestoran() {
		var dugme=document.getElementById("dodajRestoran");
		var ime=document.getElementById("imeRestorana").value;
		var tip=document.getElementById("tipRestorana").value;
		
		var ispravno=true;
		
		
	
		
		if(ime == "") {
			 document.getElementById("imeRestoranaError").innerHTML = "Morate uneti naziv restorana!";
	            ispravno=false;
	            document.getElementById("imeRestorana").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("imeRestoranaError").innerHTML = "";
           
            document.getElementById("imeRestorana").style.borderColor="";
			
		}
		
		
		if(tip == "") {
			 document.getElementById("tipRestoranaError").innerHTML = "Morate odabrati tip restorana";
	            ispravno=false;
	            document.getElementById("tipRestorana").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("tipRestoranaError").innerHTML = "";
           
            document.getElementById("tipRestorana").style.borderColor="";
			
		}
		
		
		
}