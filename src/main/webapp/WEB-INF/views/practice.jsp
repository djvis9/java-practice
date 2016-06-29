<html>
    <head>
        <!-- Bootstrap -->
        <script   src="https://code.jquery.com/jquery-2.2.4.min.js"   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="   crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
      
        <!-- React and friends -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react-dom.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/remarkable/1.6.2/remarkable.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
      
        <!-- Load this project's React application -->        
        <script type="text/babel" src="/static/practice.jsx"></script>
    </head>
    <body>
        <div class="container">
          
            <div class="jumbotron">
                <h1>
                  Java Practice
                </h1>
                <p>
                    A project to practice Java, Spring, REST, React, and Bootstrap.               
                </p>
                <p>
                    View the code for this project: <a href="https://github.com/djvis9/java-practice">https://github.com/djvis9/java-practice</a>  
                </p>
            </div>

            <div id="content">
            
                <ul id="tabs" class="nav nav-pills" data-tabs="tabs">
                    <li class="active"><a href="#balanceParentheses" data-toggle="tab">Balance Parentheses</a></li>
                    <li><a href="#balanceBraces" data-toggle="tab">Balance Braces</a></li>
                </ul>
                
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="balanceParentheses">
                        <div id="parenthesesApp"></div>
                    </div>
                    <div class="tab-pane" id="balanceBraces">
                        <div id="bracesApp"></div>
                    </div>
                </div>
                
            </div>
        </div>
    </body>
</html>