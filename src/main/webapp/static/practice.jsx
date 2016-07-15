    $(function() {
      
        /*** Begin Valid/Invalid Messages ***/
        /* These messages are shown to the user when the enter valid or invalid parentheses. */
        var ValidMessage = React.createClass({
            render: function() {
                return (
                    <div className="alert alert-success">
                        <span>Valid</span>
                    </div>
                )
            }
        });
        var InvalidMessage = React.createClass({
            render: function() {
                return (
                    <div className="alert alert-danger">
                        <span>Invalid</span>
                    </div>
                )
            }
        });
        /*** End Valid/Invalid Messages ***/
        

        
        /**
         * ValidateParentheses
         * 
         * React component that lets the user enter text and checks
         * if the text is valid by passing the text to a RESTful endpoint
         * for validation.
         * 
         * props:
         * @title a title shown to the user
         * @description a discription telling the user what is valid text
         * @validationUrl url to call when checking if user entered valid text
         */
        var ValidateText = React.createClass({
        		getInitialState: function() {
                return {
                    isValid: ""
                }
            },
            handleChange: function(e) {
                if(e.target.value != "") {
                    var pApp = this;
                    // Check if the user entered valid text
                    $.post(this.props.validationUrl, {text: e.target.value}, function(response) {
                        if(response === true) {
                            pApp.setState({isValid: <ValidMessage />});
                        } else if (response === false) {
                            pApp.setState({isValid: <InvalidMessage />});
                        }
                    });
                }
            },
            componentWillMount: function() {
                // use _.debounce so that we wait for the user to stop typing
                // before calling the server to check if the user entered valid
                // text
                this.handleChange = _.debounce(this.handleChange, 200);
            },
            onChange: function(event) {
                this.setState({isValid: ""});
                event.persist();
                this.handleChange(event);
            },
            render: function() {
                return (
                    <div>
                        <h2>{this.props.title}</h2>
                        <p>
                            {this.props.description}
                        </p>
                        <div>
                            <p>
                                <input type="text" onChange={this.onChange} />
                            </p>
                        </div>
                        {this.state.isValid}
                    </div>
                )
            }
        });
        
        ReactDOM.render(<ValidateText title="Balance Braces" description='Enter some braces (eg. "{[()]}") into the text field to check if they are valid.' validationUrl="braces" />, document.getElementById('bracesApp'));
        ReactDOM.render(<ValidateText title="Balance Parentheses" description='Enter some parentheses into the text field to check if they are valid.' validationUrl="parentheses" />, document.getElementById('parenthesesApp'));
    
        var PowersetAnswer = React.createClass({
            render: function() {
                return (
                    <div className="alert alert-success">
                        <span>Powerset{'({'}
                               {this.props.inputSet.map(function(element) {
                                   return element + ", ";
                               })}
                               {'})'} = {this.props.outputSet}
                        </span>
                    </div>
                )
            }
        });
    
        var Powerset = React.createClass({
        		getInitialState: function() {
                return {
                    isValid: ""
                }
            },
            handleChange: function(e) {
                if(e.target.value != "") {
                    var pApp = this;
                    var inputString = e.target.value;
                    $.post(this.props.url, {text: e.target.value}, function(response) {
                        var powerset = "";
                        for (var i=0; i<response.length; i++) {
                            if(i > 0) {
                                powerset += ", ";
                            }
                            powerset += "{";
                            for(var j=0; j<response[i].length; j++) {
                                if (j > 0) {
                                    powerset += ", ";
                                }
                                powerset += response[i][j];
                            }
                            powerset += "}";
                        }
                        powerset = "{ " + powerset + " }";
                        pApp.setState({isValid: <PowersetAnswer inputSet={inputString.split("")} outputSet={powerset} />});
                    });
                }
            },
            componentWillMount: function() {
                // use _.debounce so that we wait for the user to stop typing
                // before calling the server to check if the user entered valid
                // text
                this.handleChange = _.debounce(this.handleChange, 200);
            },
            onChange: function(event) {
                this.setState({isValid: ""});
                event.persist();
                this.handleChange(event);
            },
            render: function() {
                return (
                    <div>
                        <h2>{this.props.title}</h2>
                        <p>
                            {this.props.description}
                        </p>
                        <div>
                            <p>
                                <input type="text" onChange={this.onChange} />
                            </p>
                        </div>
                        {this.state.isValid}
                    </div>
                )
            }
        });
        
        ReactDOM.render(<Powerset title="Powerset" description='Enter a set of charecters (eg. "xyz") to get a powerset.' url="powerset" />, document.getElementById('powersetApp'));
    
    });