function getSource(id){
  $.ajax({
    url:"https://api.spoonacular.com/recipes/"+id+"/information?apiKey=5aa15caad28d475298b92bec26acc842",
    success: function(res) {
      document.getElementById("sourceLink").innerHTML=res.sourceUrl
      document.getElementById("sourceLink").href=res.sourceUrl
    }
  });
}

function getRecipe(q){
  $.ajax({
    url:"https://api.spoonacular.com/recipes/search?apiKey=5aa15caad28d475298b92bec26acc842&number=1&query="+q,
    success: function(res) {
      document.getElementById("output").innerHTML="<br><br><h1>"+res.results[0].title+"</h1><br><img src='"
      +res.baseUri+res.results[0].image+"' /><br>Ready in "+res.results[0].readyInMinutes+" minutes"
      getSource(res.results[0].id)
    }
  });
}

function validateUser(user,pass) {
  if (correo.length!=0 && pass.length!=0){
    alert('Se ha suscrito con éxito. Recibirá un correo cada mes desde el cual podrá acceder a la revista.');
  }else{
    alert('Introduzca valores en todos los campos.');
  }
}