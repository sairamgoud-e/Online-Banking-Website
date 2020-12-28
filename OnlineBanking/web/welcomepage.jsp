<%-- 
    Document   : welcomepage
    Created on : 31 Aug, 2020, 9:19:02 AM
    Author     : sairam goud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        
        
    </head>
    <body>
    
        <header>exi
            
            <div class="main">
                <ul>
                    <li class="active"><a href="#">Welcome</a></li>
                    <li><a href="add.html">Add to self</a></li>
                    <li><a href="transactionslogic">Transactions History</a></li>
                    <li><a href="bankacc.html">Add Bank Account</a></li>
                    <li><a href="profiledata">Profile</a></li>
                    <li><a href="bye.html" >Logout</a></li>
                     
                    
                </ul>
                
            </div>
            <div class="title"><h1><u>
                <%
                    String wname=(String)request.getAttribute("name");
                    out.println("WELCOME "+wname); 
%>
                </u></h1> 
            </div>
            <div class="button">
                <a href="transfer.html" class="btn">TRANSFER FUNDS</a>
                <!-- Trigger/Open The Modal -->
                <a href="balance" class="btn">CHECK BALANCE</a>
               <!-- <button class="btns" id="myBtn">CHECK BALANCE</button>

                     The Modal -->
                    <div id="myModal" class="modal">

                      <!-- Modal content -->
                      <div class="modal-content">
                        <div class="modal-header">
                          <span class="close">&times;</span>
                          <h2>Your Current balance is</h2>
                        </div>
                        <div class="modal-body">
                          <p>THIS MANY RUPEES </p>
                        </div>

                      </div>

                    </div>

                    
                <script>
                // Get the modal
                var modal = document.getElementById("myModal");

                // Get the button that opens the modal
                var btn = document.getElementById("myBtn");

                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[0];

                // When the user clicks the button, open the modal 
                btn.onclick = function() {
                  modal.style.display = "block";
                }

                // When the user clicks on <span> (x), close the modal
                span.onclick = function() {
                  modal.style.display = "none";
                }

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function(event) {
                  if (event.target == modal) {
                    modal.style.display = "none";
                  }
                }
                </script>

            </div>
        </header> 
    </body>
    
    
    
</html>