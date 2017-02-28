function proveraPraznihPoljaPiceIzmena() {
		var dugme=document.getElementById("izmeniPice");
		var naziv=document.getElementById("nazivPicaIzmena").value;
		
		
		var cena=document.getElementById("cenaPicaIzmena").value;
		var ispravno=true;
		
		if(naziv == ""){
            document.getElementById("nazivPicaIzmenaError").innerHTML = "Morate uneti naziv pića!";
            ispravno=false;
            document.getElementById("nazivPicaIzmena").style.borderColor="red";
            //return false;
        }
		else {
			
			document.getElementById("nazivPicaIzmenaError").innerHTML = "";
			document.getElementById("nazivPicaIzmena").style.borderColor="";
		}
		
		if(cena == ""){
            document.getElementById("cenaPicaIzmenaError").innerHTML = "Morate uneti cenu pića!";
            ispravno=false;
            document.getElementById("cenaPicaIzmena").style.borderColor="red";
            //return false;
        }
		
		else if(isNaN(cena)) {
			
				  document.getElementById("cenaPicaIzmena").value="Cena mora da bude broj";
				  ispravno=false;
		           document.getElementById("cenaPicaIzmena").style.borderColor="red";
			   
		}
		
		else {
			
			document.getElementById("cenaPicaIzmenaError").innerHTML = "";
			document.getElementById("cenaPicaIzmena").style.borderColor="";
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
	
	