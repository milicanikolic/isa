function proveraPraznihPoljaJelo() {
		var dugme=document.getElementById("dodajJelo");
		var naziv=document.getElementById("nazivJela").value;
		var vrstaJela=document.getElementById("vrstaJela").value;
		
		var cena=document.getElementById("cenaJela").value;
		var ispravno=true;
		
		if(naziv == ""){
            document.getElementById("nazivJelaError").innerHTML = "Morate uneti naziv jela!";
            ispravno=false;
            document.getElementById("nazivJela").style.borderColor="red";
            //return false;
        }
		else {
			
			document.getElementById("nazivJelaError").innerHTML = "";
			document.getElementById("nazivJela").style.borderColor="";
		}
		
		if(cena == ""){
            document.getElementById("cenaJelaError").innerHTML = "Morate uneti cenu jela!";
            ispravno=false;
            document.getElementById("cenaJela").style.borderColor="red";
            //return false;
        }
		
		else if(isNaN(cena)) {
			
				  document.getElementById("cenaJela").value="Cena mora da bude broj";
				  ispravno=false;
		           document.getElementById("cenaJela").style.borderColor="red";
			   
		}
		
		else {
			
			document.getElementById("cenaJelaError").innerHTML = "";
			document.getElementById("cenaJela").style.borderColor="";
		}
		
		if(vrstaJela == "") {
			document.getElementById("vrstaJelaError").innerHTML = "Morate izabrati vrstu jela!";
            ispravno=false;
            document.getElementById("vrstaJela").style.borderColor="red";
		}
		else {
			document.getElementById("vrstaJelaError").innerHTML = "";
			document.getElementById("vrstaJela").style.borderColor="";
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
	
	