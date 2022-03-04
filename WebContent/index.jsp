<!DOCTYPE html>
<html>
<head>
<title>Client sub chanel</title>
<link rel="stylesheet" type="text/css"  href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
	body{
		text-align: center;
		padding : 1em;
	}
	.chanelbtn button{
	margin-left:1em;
	}

</style>
</head>
<body>	
		<h2>Client Notify</h2>
		<!--  <button onclick="subchanel('Facebook')" class="btn btn-primary">SUB Facebook</button>
		<button onclick="subchanel('Youtube')" class="btn btn-primary">SUB Youtube</button>-->
		<div class="chanelbtn"></div>
		<!-- <input id="textMessage" type="text" />
		<input onclick="sendMessage()" value="Send Message" type="button" />  --><br/><br/>
		
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
				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					websocket.send(textMessage.value);
					textMessage.value = "";
				}
			}

		</script>
		<script src="https://apis.google.com/js/api.js" type="text/javascript"></script>
		<script type="text/javascript">
			  $(document).ready(function(){
				$.ajax('http://localhost:8080/ClientServer/api-chanel',{
				success: function(data){
					console.log(data);
					console.log(data.length);
					var str= "" ;
					for(let i = 0; i < data.length; i++){
						str+= "<button data-id='"+data[i]+"' class='btn btn-primary'>SUB "+data[i]+"</button>";
					}
					$(".chanelbtn").html(str);
					 
					
					$('button').click(function(){
						alert("You Subscribe  "+$(this).data('id'));
						if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
							websocket.send("SUB "+$(this).data('id'));
							}
					})
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