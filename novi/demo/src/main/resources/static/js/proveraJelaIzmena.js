function proveraPraznihPoljaJeloIzmena() {
		var dugme=document.getElementById("izmeniJelo");
		var naziv=document.getElementById("nazivJelaIzmena").value;
		
		
		var cena=document.getElementById("cenaJelaIzmena").value;
		var ispravno=true;
		
		if(naziv == ""){
            document.getElementById("nazivJelaIzmenaError").innerHTML = "Morate uneti naziv jela!";
            ispravno=false;
            document.getElementById("nazivJelaIzmena").style.borderColor="red";
            //return false;
        }
		else {
			
			document.getElementById("nazivJelaIzmenaError").innerHTML = "";
			document.getElementById("nazivJelaIzmena").style.borderColor="";
		}
		
		if(cena == ""){
            document.getElementById("cenaJelaIzmenaError").innerHTML = "Morate uneti cenu jela!";
            ispravno=false;
            document.getElementById("cenaJelaIzmena").style.borderColor="red";
            //return false;
        }
		
		else if(isNaN(cena)) {
			
				  document.getElementById("cenaJelaIzmena").value="Cena mora da bude broj";
				  ispravno=false;
		           document.getElementById("cenaJelaIzmena").style.borderColor="red";
			   
		}
		
		else {
			
			document.getElementById("cenaJelaIzmenaError").innerHTML = "";
			document.getElementById("cenaJelaIzmena").style.borderColor="";
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
	
	