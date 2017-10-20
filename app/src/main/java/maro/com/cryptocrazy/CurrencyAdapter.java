package maro.com.cryptocrazy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static maro.com.cryptocrazy.R.id.currency;

/**
 * Created by Emmanuel on 10/10/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private List<Currency> currencys;
    private Context context;

    public CurrencyAdapter(List<Currency> currencys, Context context) {
        this.currencys = currencys;
        this.context = context;
    }



    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    //set to the Values to the views on the card_item
    public void onBindViewHolder(CurrencyAdapter.ViewHolder holder, int position) {
        final Currency currency = currencys.get(position);
        holder.imageView.setImageResource(currency.getImageId());
        holder.currencyTextView.setText(currency.getCurrency());
        holder.symbolTextView.setText(currency.getSymbol());
        holder.valueTextView.setText(currency.getValue());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Conversion.class);
                intent.putExtra("query_value", currency.getValue());
                intent.putExtra("currencyType",currency.getCurrency());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currencys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Define the views on the card_item
        public TextView currencyTextView;
        public TextView valueTextView;
        public LinearLayout linearLayout;
        public TextView symbolTextView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            currencyTextView= (TextView)itemView.findViewById(currency);
            valueTextView= (TextView)itemView.findViewById(R.id.value);
            symbolTextView= (TextView)itemView.findViewById(R.id.symbol);
            imageView=(ImageView)itemView.findViewById(R.id.flag) ;
            linearLayout= (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
