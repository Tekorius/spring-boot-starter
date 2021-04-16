# Security Module

This is where we store configurations related to security
of web-core.

Different modules may have different configuration classes
for its endpoints.

As of now there are the following endpoint security 
configurations:

* `GlobalSecurityConfig` - A default fallback security
  config that will catch all requests if they haven't
  been handled by other security configs. Also contains
  system wide security configurations such as password
  encoders and annotations.
* `PublicSecurityConfig` - Configuration of public endpoints
  that can be reached without a token. Pretty much all
  endpoints starting with `/public` will be handled by this
  config.
* `AdminSecurityConfig` - Handles endpoints starting with
  `/admin` and requires current user to have `ROLE_ADMIN`
  to access those endpoints. Since admins usually have
  their own separate endpoints and extra security requirements
  this config is separate from the main config. Also
  having this config separate allows for easier refactoring
  of admin functionality into a separate submodule.
* `SwaggerSecurityConfig` - Exposes endpoints provided by
  SpringDoc plugin.
* `DevSecurityConfig` - Configuration which only gets
  loaded when `dev` profile is active. Allows accessing
  development features such as H2-console.