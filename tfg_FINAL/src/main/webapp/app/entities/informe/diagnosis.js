   // Diagnosis suggested by Naive-Bayes Classifier (Weka API)
   function starAPI (){
    var asyncRequest;
    try {
     asyncRequest = new XMLHttpRequest();
     asyncRequest.addEventListener("readystatechange", this.Diagnosis, false);
     asyncRequest.open('GET', '/Test', true);    //   /Test is url to Servlet!
     asyncRequest.send(null);

    } catch (exception) {
      alert("failed");
    }
  }
  function Diagnosis(){
   // const asyncRequest = new XMLHttpRequest();
   // if (asyncRequest.readyState == 4 && asyncRequest.status==200){
   //   const text = document.getElementById("text");
   //   text.innerHTML = asyncRequest.responseText;
   // }
  }

window.addEventListener("load", startAPI(), false);
