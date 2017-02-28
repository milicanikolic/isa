function proveraPraznihPoljaIzmena() {
		var dugme=document.getElementById("dugmeIzmenaG");
		var sifra1=document.getElementById("sifraIzmenaG").value;
		var email=document.getElementById("emailIzmenaG").value;
		var korisnickoIme=document.getElementById("kImeIzmenaG").value;
		var ime=document.getElementById("imeIzmenaG").value;
		var prezime=document.getElementById("prezimeIzmenaG").value;
		
		var patt=new RegExp("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");
		var ispravno=true;
		
		var slova=new RegExp("^[A-Za-z]*$");
		
		
		if(!slova.test(ime)) {
			 document.getElementById("imeIzmenaError").innerHTML = "Ime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("imeIzmenaG").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("imeIzmenaError").innerHTML = "";
           
            document.getElementById("imeIzmenaG").style.borderColor="";
			
		}
		
		if(!slova.test(prezime)) {
			 document.getElementById("prezimeIzmenaError").innerHTML = "Prezime se mora sastojati od slova!";
	            ispravno=false;
	            document.getElementById("prezimeIzmenaG").style.borderColor="red";
			
			
		}
		else {
			document.getElementById("prezimeIzmenaError").innerHTML = "";
          
           document.getElementById("prezimeIzmenaG").style.borderColor="";
			
		}
		
		
		
		
		if(sifra1 == ""){
            document.getElementById("sifraIzmenaError").innerHTML = "Morate uneti šifru!";
            ispravno=false;
            document.getElementById("sifraIzmenaG").style.borderColor="red";
            //return false;
        }
		else {

			document.getElementById("sifraIzmenaError").innerHTML = "";
			document.getElementById("sifraIzmenaG").style.borderColor="";
		}
 
        
        
        if(email == ""){
            document.getElementById("emailIzmenaError").innerHTML = "Morate uneti e-mail!";
            ispravno=false;
            document.getElementById("emailIzmenaG").style.borderColor="red";
            //return false;
        }
        else if(!patt.test(email)) {
        	document.getElementById("emailIzmenaError").innerHTML = "Nevalidna e-mail adresa!";
            ispravno=false;
            document.getElementById("emailIzmenaG").style.borderColor="red";
        	
        }
        
        else {
        	
        	document.getElementById("emailIzmenaError").innerHTML = "";
            document.getElementById("emailIzmenaG").style.borderColor="";
        }
        
        if(korisnickoIme == ""){
            document.getElementById("kImeIzmenaError").innerHTML = "Morate uneti korisničko ime!";
            ispravno=false;
            document.getElementById("kImeIzmenaG").style.borderColor="red";
            //return false;
        }
        
        else {
        	
        	document.getElementById("kImeIzmenaError").innerHTML = "";
           
            document.getElementById("kImeIzmenaG").style.borderColor="";
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
		
	
	

	