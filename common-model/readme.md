# Common Model

Multiple microservices may want to share data between
each other. This submodule decouples microservices from
depending on each other and allows us to avoid code (model)
duplication.

The idea is that this submodule contains Request/Response
classes. It can also later be packaged and made public
without actually exposing the implementation of web core.