import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { EstadisticaPzNeuronormaService } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma.service';
import { IEstadisticaPzNeuronorma, EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

describe('Service Tests', () => {
  describe('EstadisticaPzNeuronorma Service', () => {
    let injector: TestBed;
    let service: EstadisticaPzNeuronormaService;
    let httpMock: HttpTestingController;
    let elemDefault: IEstadisticaPzNeuronorma;
    let expectedResult: IEstadisticaPzNeuronorma | IEstadisticaPzNeuronorma[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EstadisticaPzNeuronormaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EstadisticaPzNeuronorma(0, 0, 0);
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

      it('should create a EstadisticaPzNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EstadisticaPzNeuronorma())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EstadisticaPzNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            ajusteEstudios: 1,
            pz: 1
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

      it('should return a list of EstadisticaPzNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            ajusteEstudios: 1,
            pz: 1
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

      it('should delete a EstadisticaPzNeuronorma', () => {
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
