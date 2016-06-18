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
         * React component that lets the user enter text and checks if the
         * the text contains valid parentheses.
         */
        var ValidateParentheses = React.createClass({
        		getInitialState: function() {
                return {
                    isValid: ""
                }
            },
            handleChange: function(e) {
                if(e.target.value != "") {
                    var pApp = this;
                    // Check if the user entered valid paretheses
                    $.post('parentheses', {text: e.target.value}, function(response) {
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
                // parentheses
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
                        <h2>Balance Parentheses</h2>
                        <p>
                            Enter some parentheses into the text field to check if they are valid.
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
      
        ReactDOM.render(<ValidateParentheses />, document.getElementById('practiceApp'));
    });