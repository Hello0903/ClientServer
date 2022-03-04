<!DOCTYPE html>
<html>
<head>
<title>Admin sent notify</title>
<link rel="stylesheet" type="text/css"  href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<style type="text/css">
	body{
		text-align: center;
		padding: 1em;
	}
	#formset input{
	margin-left:1em;
	}

</style>
</head>
<body>	
		<h2>Admin sent Notify</h2>
		<h4>Choose chanel</h4>
		<form id="formset">
		<!--	<input type="radio" name="chanel" value="Facebook"> Facebook
			<input type="radio" name="chanel" value="Youtube"> Youtube -->
		</form>
		<h3>Write message</h3>
		<textarea id="textMessage" type="text"  rows="3" cols="50" /></textarea><br>
		<input onclick="sendMessage()" value="Send Notify" type="button" class="btn-primary btn" /> <br/><br/>
		
		<textarea id="textAreaMessage" rows="10" cols="50" readonly></textarea>



		

		<script type="text/javascript">
			var websocket = new WebSocket("ws://localhost:8080/ClientServer/chatRoomServer");
				websocket.onopen = function(message) {processOpen(message);};
				websocket.onmessage = function(message) {processMessage(message);};
				websocket.onclose = function(message) {processClose(message);};
				websocket.onerror = function(message) {processError(message);};

			function processOpen(message) {
				textAreaMessage.value += "Server connect... \n";
			}
			function processMessage(message) {
				console.log(message);
				textAreaMessage.value += message.data + " \n";
			}
			function processClose(message) {
				textAreaMessage.value += "Server Disconnect... \n";
			}
			function processError(message) {
				textAreaMessage.value += "Error... " + message +" \n";
			}

			function sendMessage() {
				//
					var checkbox = document.getElementsByName("chanel");
					var namechanel ;
                	for (var i = 0; i < checkbox.length; i++){
                    if (checkbox[i].checked === true){
                        namechanel = checkbox[i].value;
                    }
                }
				//


				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					websocket.send("PUB "+namechanel+" "+textMessage.value);
					textMessage.value = "";
				}
			}

		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://apis.google.com/js/api.js" type="text/javascript"></script>
		<script type="text/javascript">
			  $(document).ready(function(){
				$.ajax('http://localhost:8080/ClientServer/api-chanel',{
				success: function(data){
					console.log(data);
					console.log(data.length);
					var str= "" ;
					var check ;
					for(let i = 0; i < data.length; i++){
						if(i==0) check = "checked = 'true'";
						else check = "";
					  str += "<input type='radio'"+check+" name='chanel' value='"+data[i]+"'> "+data[i];
					}
					$("#formset").html(str);
				}
				
				})
				function subchanel(chanel) {
				// body...
				alert("adads");
				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					websocket.send("SUB "+chanel);
					}
				}
			  })
			  
			 
			
		</script>
</body>
</html>