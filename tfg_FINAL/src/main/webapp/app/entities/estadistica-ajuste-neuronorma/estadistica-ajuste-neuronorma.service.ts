import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

type EntityResponseType = HttpResponse<IEstadisticaAjusteNeuronorma>;
type EntityArrayResponseType = HttpResponse<IEstadisticaAjusteNeuronorma[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaAjusteNeuronormaService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-ajuste-neuronormas';

  constructor(protected http: HttpClient) {}

  create(estadisticaAjusteNeuronorma: IEstadisticaAjusteNeuronorma): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaAjusteNeuronorma>(this.resourceUrl, estadisticaAjusteNeuronorma, { observe: 'response' });
  }

  update(estadisticaAjusteNeuronorma: IEstadisticaAjusteNeuronorma): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaAjusteNeuronorma>(this.resourceUrl, estadisticaAjusteNeuronorma, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaAjusteNeuronorma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaAjusteNeuronorma[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
