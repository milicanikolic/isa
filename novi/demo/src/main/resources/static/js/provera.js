function proveraSifre(){
	var dugme=document.getElementById("dugmeRegistruj");
	var sifra1=document.getElementById("sifra").value;
	var sifra2=document.getElementById("sifra2").value;
	
	if(sifra1!=sifra2){
		dugme.disabled=true;
		document.getElementById("sifra2").style.borderColor="red";
		
	}else{
		dugme.disabled=false;
		document.getElementById("sifra2").style.borderColor="";
		
	}
}

function proveraPraznihPolja(){
	if(document.getElementById("ime").value==""){
		alert("sasd");
	}
	
}
	