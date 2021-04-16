# Common Security Module

Multiple microservices require sharing of security features
such as User principals and JWT rules. Having this as a
separate submodule allows us to set this submodule as a
dependency of new microservices and manage shared security
features from a central location.