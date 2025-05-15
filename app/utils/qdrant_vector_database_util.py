from qdrant_client import QdrantClient, models

# Retrieve random documents from Qdrant using embeddings
def get_random_data_from_qdrant(qdrant_client: QdrantClient, collection_name:str, num_docs=5):
    return_list = [] 

    response = qdrant_client.query_points(
        collection_name=collection_name,
        query=models.SampleQuery(sample=models.Sample.RANDOM),
        limit=num_docs,
        with_payload=True
    )
        
    for hit in response.points:
        if hit.payload['doc_type'] == "image":
            return_list.append(hit.payload['summary'])
        else:
            return_list.append(hit.payload['full_content'])
    
    return return_list