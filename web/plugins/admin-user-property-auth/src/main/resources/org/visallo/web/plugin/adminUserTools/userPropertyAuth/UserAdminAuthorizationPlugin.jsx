define([
    'react',
    'public/v1/api',
    'jsx!util/react-alert'
], function (React,
             visallo,
             ReactAlert) {

    const UserAdminAuthorizationPlugin = React.createClass({
        dataRequest: null,

        getInitialState() {
            return {
                authorizations: this.props.user.authorizations,
                saveInProgress: false
            };
        },

        componentWillMount() {
            visallo.connect()
                .then(({dataRequest})=> {
                    this.dataRequest = dataRequest;
                })
        },

        handleAddAuthorizationSubmit(e) {
            e.preventDefault();
            this.addAuthorization(this.refs.addAuthorization.value);
        },

        addAuthorization(authorization){
            this.setState({
                saveInProgress: true
            });

            const newAuthorizations = this.state.authorizations.concat([authorization]);

            this.dataRequest('com-visallo-userAdminAuthorization', 'userAuthAdd', this.props.user.userName, authorization)
                .then(() => {
                    this.refs.addAuthorization.value = '';
                    this.setState({
                        authorizations: newAuthorizations,
                        saveInProgress: false,
                        error: null
                    });
                })
                .catch((e) => {
                    this.setState({error: e, saveInProgress: false});
                });
        },

        handleAuthorizationDeleteClick(authorization) {
            this.setState({
                saveInProgress: true
            });

            const newAuthorizations = this.state.authorizations.filter((a)=>a !== authorization);

            this.dataRequest('com-visallo-userAdminAuthorization', 'userAuthRemove', this.props.user.userName, authorization)
                .then(() => {
                    this.setState({
                        authorizations: newAuthorizations,
                        saveInProgress: false,
                        error: null
                    });
                })
                .catch((e) => {
                    this.setState({error: e});
                });
        },

        handleAlertDismiss() {
            this.setState({
                error: null
            });
        },

        render() {
            return (
                <div>
                    <div className="nav-header">Authorizations</div>
                    <ReactAlert error={this.state.error} onDismiss={this.handleAlertDismiss}/>
                    <ul>
                        { this.state.authorizations.map((auth) => (
                            <li key={auth} className="auth-item highlight-on-hover">
                                <button className="btn btn-mini btn-danger show-on-hover"
                                        onClick={()=>this.handleAuthorizationDeleteClick(auth)}
                                        disabled={this.state.saveInProgress ? 'disabled' : ''}>
                                    {i18n('admin.user.editor.userAdminAuthorization.deleteAuthorization')}
                                </button>
                                <span style={{lineHeight: '1.2em'}}>{auth}</span>
                            </li>
                        )) }
                    </ul>

                    <form onSubmit={this.handleAddAuthorizationSubmit}>
                        <input style={{marginTop: '0.5em'}} className="auth" ref="addAuthorization"
                               placeholder="Add Authorization"
                               type="text"
                               disabled={this.state.saveInProgress ? 'disabled' : ''}/>
                        <button
                            disabled={this.state.saveInProgress ? 'disabled' : ''}>
                            {i18n('admin.user.editor.userAdminAuthorization.addAuthorization')}
                        </button>
                    </form>
                </div>
            );
        }
    });

    return UserAdminAuthorizationPlugin;
});
