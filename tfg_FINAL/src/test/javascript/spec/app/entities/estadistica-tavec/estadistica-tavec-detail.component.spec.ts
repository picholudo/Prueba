import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTAVECDetailComponent } from 'app/entities/estadistica-tavec/estadistica-tavec-detail.component';
import { EstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';

describe('Component Tests', () => {
  describe('EstadisticaTAVEC Management Detail Component', () => {
    let comp: EstadisticaTAVECDetailComponent;
    let fixture: ComponentFixture<EstadisticaTAVECDetailComponent>;
    const route = ({ data: of({ estadisticaTAVEC: new EstadisticaTAVEC(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTAVECDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaTAVECDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaTAVECDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaTAVEC on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaTAVEC).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
