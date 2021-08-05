import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { EstadisticaAjusteNeuronormaService } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma.service';
import { IEstadisticaAjusteNeuronorma, EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

describe('Service Tests', () => {
  describe('EstadisticaAjusteNeuronorma Service', () => {
    let injector: TestBed;
    let service: EstadisticaAjusteNeuronormaService;
    let httpMock: HttpTestingController;
    let elemDefault: IEstadisticaAjusteNeuronorma;
    let expectedResult: IEstadisticaAjusteNeuronorma | IEstadisticaAjusteNeuronorma[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EstadisticaAjusteNeuronormaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EstadisticaAjusteNeuronorma(0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EstadisticaAjusteNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EstadisticaAjusteNeuronorma())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EstadisticaAjusteNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            scaledScore: 1,
            ajusteEstudios: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EstadisticaAjusteNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            scaledScore: 1,
            ajusteEstudios: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EstadisticaAjusteNeuronorma', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
