package org.visallo.web.routes.vertex;

import com.google.inject.Inject;
import com.v5analytics.webster.ParameterizedHandler;
import com.v5analytics.webster.annotations.Handle;
import com.v5analytics.webster.annotations.Optional;
import com.v5analytics.webster.annotations.Required;
import org.vertexium.Authorizations;
import org.vertexium.Graph;
import org.vertexium.Vertex;
import org.visallo.core.exception.VisalloResourceNotFoundException;
import org.visallo.core.model.termMention.TermMentionRepository;
import org.visallo.web.clientapi.model.ClientApiTermMentionsResponse;
import org.visallo.web.parameterProviders.ActiveWorkspaceId;
import org.visallo.web.routes.GetResolvedToBase;

public class VertexGetResolvedTo extends GetResolvedToBase implements ParameterizedHandler {
    private final Graph graph;

    @Inject
    public VertexGetResolvedTo(
            Graph graph,
            TermMentionRepository termMentionRepository
    ) {
        super(termMentionRepository);
        this.graph = graph;
    }

    @Handle
    public ClientApiTermMentionsResponse handle(
            @Required(name = "graphVertexId") String graphVertexId,
            @Optional(name = "propertyKey") String propertyKey,
            @Optional(name = "propertyName") String propertyName,
            @ActiveWorkspaceId String workspaceId,
            Authorizations authorizations
    ) throws Exception {
        Vertex vertex = graph.getVertex(graphVertexId, authorizations);
        if (vertex == null) {
            throw new VisalloResourceNotFoundException(String.format("vertex %s not found", graphVertexId));
        }

        return getClientApiTermMentionsResponse(
                vertex,
                propertyKey,
                propertyName,
                workspaceId,
                authorizations
        );
    }
}
