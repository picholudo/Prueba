import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

type EntityResponseType = HttpResponse<IEstadisticaPzNeuronorma>;
type EntityArrayResponseType = HttpResponse<IEstadisticaPzNeuronorma[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaPzNeuronormaService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-pz-neuronormas';

  constructor(protected http: HttpClient) {}

  create(estadisticaPzNeuronorma: IEstadisticaPzNeuronorma): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaPzNeuronorma>(this.resourceUrl, estadisticaPzNeuronorma, { observe: 'response' });
  }

  update(estadisticaPzNeuronorma: IEstadisticaPzNeuronorma): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaPzNeuronorma>(this.resourceUrl, estadisticaPzNeuronorma, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaPzNeuronorma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaPzNeuronorma[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
