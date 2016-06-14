define([
    'react',
    'public/v1/api',
    'jsx!./WorkspaceList',
    'jsx!./LoadUser'
], function (React,
             visallo,
             WorkspaceList,
             LoadUser) {

    const UserAdminPlugin = React.createClass({
        dataRequest: null,

        getInitialState() {
            return {
                user: null,
                reloadUser: false
            };
        },

        componentWillMount() {
            visallo.connect()
                .then(({dataRequest})=> {
                    this.dataRequest = dataRequest;
                });

            const privilegesPlugins = visallo.registry.extensionsForPoint('org.visallo.admin.user.privileges');
            if (privilegesPlugins.length != 1) {
                throw new Error('Exactly one "org.visallo.admin.user.privileges" is required. Found: ' + privilegesPlugins.length);
            }

            const authorizationsPlugins = visallo.registry.extensionsForPoint('org.visallo.admin.user.authorizations');
            if (authorizationsPlugins.length != 1) {
                throw new Error('Exactly one "org.visallo.admin.user.authorizations" is required. Found: ' + authorizationsPlugins.length);
            }

            Promise.all([privilegesPlugins[0].componentPath, authorizationsPlugins[0].componentPath]
                .map(Promise.require))
                .spread((PrivilegesComponent, AuthorizationsComponent) => {
                    this.PrivilegesPlugin = PrivilegesComponent;
                    this.AuthorizationsPlugin = AuthorizationsComponent;
                });
        },

        handleUserLoaded(user) {
            this.setState({
                user: user,
                reloadUser: false
            });
        },

        handleWorkspaceChanged() {
            this.setState({
                reloadUser: true
            });
        },

        render() {
            return (
                <div className="user-admin">
                    <LoadUser
                        reload={this.state.reloadUser}
                        username={this.state.user ? this.state.user.userName : ''}
                        onUserLoaded={this.handleUserLoaded}/>
                    { this.state.user ? (
                        <div>
                            <div className="nav-header">User Info</div>
                            <ul>
                                <li>
                                    <label className="nav-header">ID</label>
                                    <span>{this.state.user.id}</span>
                                </li>
                                <li>
                                    <label className="nav-header">E-Mail</label>
                                    <span>{this.state.user.email || i18n('admin.user.editor.notSet')}</span>
                                </li>
                                <li>
                                    <label className="nav-header">Display Name</label>
                                    <span>{this.state.user.displayName || i18n('admin.user.editor.notSet')}</span>
                                </li>
                                <li>
                                    <label className="nav-header">Status</label>
                                    <span>{this.state.user.status}</span>
                                </li>
                            </ul>

                            <div>
                                <this.PrivilegesPlugin user={this.state.user}/>
                            </div>

                            <div>
                                <this.AuthorizationsPlugin user={this.state.user}/>
                            </div>

                            <div>
                                <WorkspaceList user={this.state.user}
                                               onWorkspaceChanged={this.handleWorkspaceChanged}
                                />
                            </div>
                        </div>
                    ) : null }
                </div>
            );
        }
    });

    return UserAdminPlugin;
});
