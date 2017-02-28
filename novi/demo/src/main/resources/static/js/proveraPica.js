function proveraPraznihPoljaPice() {
		var dugme=document.getElementById("dodajPice");
		var naziv=document.getElementById("nazivPica").value;
		var vrstaJela=document.getElementById("vrstaPica").value;
		
		var cena=document.getElementById("cenaPica").value;
		var ispravno=true;
		
		if(naziv == ""){
            document.getElementById("nazivPicaError").innerHTML = "Morate uneti naziv pića!";
            ispravno=false;
            document.getElementById("nazivPica").style.borderColor="red";
            //return false;
        }
		else {
			
			document.getElementById("nazivPicaError").innerHTML = "";
			document.getElementById("nazivPica").style.borderColor="";
		}
		
		if(cena == ""){
            document.getElementById("cenaPicaError").innerHTML = "Morate uneti cenu pića!";
            ispravno=false;
            document.getElementById("cenaPica").style.borderColor="red";
            //return false;
        }
		
		else if(isNaN(cena)) {
			
				  document.getElementById("cenaPica").value="Cena mora da bude broj";
				  ispravno=false;
		           document.getElementById("cenaPica").style.borderColor="red";
			   
		}
		
		else {
			
			document.getElementById("cenaPicaError").innerHTML = "";
			document.getElementById("cenaPica").style.borderColor="";
		}
		
		if(vrstaJela == "") {
			document.getElementById("vrstaPicaError").innerHTML = "Morate izabrati vrstu pića!";
            ispravno=false;
            document.getElementById("vrstaPica").style.borderColor="red";
		}
		else {
			document.getElementById("vrstaPicaError").innerHTML = "";
			document.getElementById("vrstaPica").style.borderColor="";
		}
		
		 if(ispravno==false){
	        	dugme.disabled=true;
	        	return false;
	        }
	        else{
	        	dugme.disabled=false;
	        	return true;
	        }
		
	}
	
	